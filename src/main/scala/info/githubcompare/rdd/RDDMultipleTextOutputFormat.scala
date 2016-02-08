package info.githubcompare.rdd

import org.apache.hadoop.io.NullWritable
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat

class RDDMultipleTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {
  override def generateActualKey(key: Any, value: Any) = NullWritable.get()
  override def generateFileNameForKeyValue(key: Any, value: Any, name: String) = key.toString
}
