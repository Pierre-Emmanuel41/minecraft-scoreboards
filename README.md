# Minecraft-scoreboards

This plugin propose an easy way to add several lines to an objective in order to update them automatically.

# Register as maven dependency

It is easy to register this project as dependency for your own project. To do so, you need to download this project.

The easiest way to do so is to use the following git command line 

```git
git clone https://github.com/Pierre-Emmanuel41/dictionary.git --recursive
```
Indeed, this project depends on the project [minecraft-managers](https://github.com/Pierre-Emmanuel41/minecraft-managers) and need to be downloaded on your machine to avoid compilation errors.

Then, you need to run the following maven command line twice : 

```maven
mvn clean package install

```
The first one in the project folder : <code>/minecraft-scoreboards/minecraft-managers</code> and the second one in the folder <code>/minecraft-scoreboards</code>. This will create the archive of the two projects in your .m2 folder.

Finally, in the pom.xml of your project, you have to add the following lines :

```xml
<dependency>
	<groupId>fr.pederobien</groupId>
	<artifactId>dictionary</artifactId>
	<version>1.0</version>
</dependency>
```
You can now use features provided by this api in your project.

To see how you can use those features, please have a look to [This tutorial](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/Tutorial.md)