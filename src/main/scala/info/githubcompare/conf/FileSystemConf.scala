package info.githubcompare.conf

trait FileSystemConf {
  def cacheDir(): String
  def storeDir(): String
}
