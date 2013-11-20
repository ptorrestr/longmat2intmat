package hujo.deri.org.longMat2intMat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.SequenceFile.CompressionType;
import org.apache.log4j.Logger;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.SequentialAccessSparseVector;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VectorWritable;
import org.apache.mahout.math.Vector.Element;

public class Long2Int {
	private final static Logger log = Logger.getLogger(Long2Int.class);
	
	public void readMahoutSequenceFile(String input, String output, String dict) 
	{
		Configuration conf = new Configuration();
		FileSystem fs;
		SequenceFile.Reader read;
		try {
			log.info("Starting Lon2Int");
			fs = FileSystem.get(conf);
			read = new SequenceFile.Reader(fs, new Path(dict), conf);
			IntWritable dicKey = new IntWritable();
			Text text = new Text();
			Map<Integer, String> dictionaryMap = new HashMap<Integer, String>();
			try {
				while (read.next(text, dicKey)) {
					dictionaryMap.put(Integer.parseInt(dicKey.toString()), 
							text.toString());
				}
			}
			catch (NumberFormatException e) {
				log.error(e);
			}
			catch (IOException e) {
				log.error(e);
			}
			read.close();
			read = new SequenceFile.Reader(fs, new Path(input), conf);
			SequenceFile.Writer write = SequenceFile.createWriter(fs, conf, 
					new Path(output), IntWritable.class, VectorWritable.class, 
					CompressionType.BLOCK, read.getCompressionCodec());
			LongWritable key = new LongWritable();
			VectorWritable value = new VectorWritable();
			RandomAccessSparseVector vect;
			while ( read.next(key, value) ) {
				NamedVector namedVector = (NamedVector) value.get();
				vect = (RandomAccessSparseVector) namedVector.getDelegate();
				for ( Element e : vect.all() ) {
					log.info("Token: " + dictionaryMap.get(e.index()) + 
							", TF-IDF weight: " + e.get());
					write.append(new IntWritable(
							Integer.valueOf(key.toString())), value);
				}
			}
			log.info("Finished");
			read.close();
			write.close();
		}
		catch (IOException e) {
			log.error(e);
		}
	}
}
