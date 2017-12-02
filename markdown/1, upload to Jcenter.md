# Android Studio upload code to JCenter
## 1, 创建 [Bintray](https://bintray.com/) 账号。

![100x100](http://www.bb-sz.com/md/picture/10000.jpg)

## 2 ,准备好要上传的项目。

## 3, 在项目build.gradle中添加依赖:
如图:![100x100](http://www.bb-sz.com/md/picture/10001.jpg)

        classpath 'com.jfrog.bintray.gradle:gradle-bintray-plugin:1.3.1'
        classpath "org.jfrog.buildinfo:build-info-extractor-gradle:3.1.1"
        classpath 'com.github.dcendents:android-maven-gradle-plugin:2.0'

## 4，在app的build.gradle中最后添加【自行修改对应的值】：

    ext {
        bintrayRepo = 'maven'
        bintrayName = 'fzhelper'                                //项目名字

        publishedGroupId = 'org.didd.dev'                //GroupId,这个要别人引用的时候会用到，
        libraryName = 'fzhelper'                         //这个是项目名字
        artifact = 'fzhelper'                                   //artifactId，这个也是别人引用的时候，会用到，它要和Module名字一样

        libraryDescription = 'Accessibility Utils'       //项目描述

        siteUrl = 'https://github.com/Jiangxuewu/AccessibilityUtils'          //把项目分享到github后的项目地址
        gitUrl = 'https://github.com/Jiangxuewu/AccessibilityUtils.git'       //分享后的项目git地址

        libraryVersion = '1.0.1'
        developerId = 'org.didd.dev'                             //这个开发者id，自己写
        developerName = 'skycar'                           //这个是开发者名字
        developerEmail = 'dev@bb-sz.com'                    //开发者邮箱

        licenseName = 'The Apache Software License, Version 2.0'     //开源协议
        licenseUrl = 'http://www.apache.org/licenses/LICENSE-2.0.txt'
        allLicenses = ["Apache-2.0"]
    }
    apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/installv1.gradle'
    apply from: 'https://raw.githubusercontent.com/nuuneoi/JCenter/master/bintrayv1.gradle'

## 5，在local.properties中添加【需要修改成自己的user与apikey】:
    bintray.user=jiangxuewu
    bintray.apikey=xxxxce6ab3a7axxxxxxxxxxxxxxxxxxxxxxxx

## 6,在gradle.properties中添加【自行修改成自己的jdk】:
    org.gradle.java.home=D:\\Program Files\\Android\\Android Studio\\jre

## 7, 在Terminal中分别运行命令:
    gradlew install
    gradlew bintrayUpload

    如果出现失败，错误为Unsupported major.minor version 52.0，则需要修改第6步中jdk的版本为更高的版本。
## 8, 上面两个命令运行成功后，登录[Bintray](https://bintray.com/) 账号，找到刚刚上传的项目,
路径是jiangxuewu/maven/fzhelper
![100x100](http://www.bb-sz.com/md/picture/10002.jpg)

点击右下方的app to jcenter按钮![100x100](http://www.bb-sz.com/md/picture/10003.jpg),

下一步中直接点击send按钮即可，无需填写什么内容

![](http://www.bb-sz.com/md/picture/10004.jpg)

然后等待几个小时就可以在任何app的build.gradle中引用了

    compile 'org.didd.dev:fzhelper:1.0.1'

![](http://www.bb-sz.com/md/picture/10005.jpg)