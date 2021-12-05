# Presentation

This plugin propose an easy way to add several lines to an objective in order to update them automatically.

# Download

# Download

First you need to download this project on your computer. To do so, you can use the following command line :

```git
git clone https://github.com/Pierre-Emmanuel41/minecraft-dictionary.git --recursive
```

and then double click on the deploy.bat file. This will deploy this project and all its dependencies on your computer. Which means it generates the folder associated to this project and its dependencies in your .m2 folder. Once this has been done, you can add the project as maven dependency on your maven project :

```xml
<dependency>
	<groupId>fr.pederobien</groupId>
	<artifactId>minecraft-scoreboards</artifactId>
	<version>2.0_MC_1.13.2-SNAPSHOT</version>
</dependency>
```
You can now use features provided by this api in your project.

To see how you can use those features, please have a look to [This tutorial](https://github.com/Pierre-Emmanuel41/minecraft-scoreboards/blob/master/Tutorial.md)