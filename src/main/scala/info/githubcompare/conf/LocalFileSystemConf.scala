package info.githubcompare.conf

import info.githubcompare.AppInfo

class LocalFileSystemConf extends FileSystemConf {
  private val userDir = System.getProperty("user.home")

  def cacheDir() = s"$userDir/.${AppInfo.name}/cache/"
  def storeDir() = s"$userDir/.${AppInfo.name}/store/"
}
