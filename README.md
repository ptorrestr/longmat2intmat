LongMat2IntMat
=============

Transform Lucene index representation into int-vector Map. This transform is needed by almost every algorithm in Mahout

Compile
------
`mvn package`

Execute
------
`hadoop jar target/*-job.tar -input INPUT -dict DICT -output OUTPUT`

Where:
* INPUT is the lucene.vector output file
* DICT is the sequenceFile dictionary (not the plain file)
* OUTPUT is the path where store the result
