# !/usr/bin/env python3
# _*_ coding: utf-8 _*_
import sys
import os
import re
import shutil


def isModule(rootPath):
    gradleProperties = os.path.join(rootPath, "gradle.properties")
    isModule = False
    if not os.path.exists(gradleProperties):
        print("file path %s is not exist" % gradleProperties)
        return None
    with open(gradleProperties, mode="r", encoding="utf-8") as rf:
        lines = rf.readlines()
    for line in lines:
        # 查找不以双斜杠开头的module，即注释了的不要
        if not re.match('^//',line.lstrip()):
            if re.match('isModule',line.lstrip()):
                isModuleValue = line.split("=", 1)[1].strip()
                isModule = isModuleValue == str('true')
    return isModule




# 读取android根目录下的setting.gradle文件，选出所有的include的module，生成列表
def get_module_name(rootPath):
    # 打开android根目录下的setting.gradle文件
    setPath = os.path.join(rootPath, "settings.gradle")
    if not os.path.exists(setPath):
        print("file path %s is not exist" % setPath)
        return None
    print("setting.gradle path is %s" %  setPath)
    with open(setPath, mode="r", encoding="utf-8") as rf:
        lines = rf.readlines()
    # with open(setPath, mode="w", encoding="utf-8") as wf:
    #     pass
    _mod_list = []
    for line in lines:
        # print("line = %s" % line)
        # 查找不以双斜杠开头的module，即注释了的不要
        if not re.match('^//',line.lstrip()):
            d = re.findall('\':(.*?)\'',line)
            for each in d:
                _mod_list.append(each)
            d_d = re.findall('\":(.*?)\"',line)
            for each in d_d:
                _mod_list.append(each)
    return _mod_list



# 获取Module下的所有包含  apply plugin: 'com.android.application' 的module
def scanModuleList(rootPath,moduleList):
    modulePathList = []
    for name in moduleList:
        modulePath = os.path.join(rootPath, name)
        buildPath = os.path.join(modulePath, "build.gradle")
        if os.path.exists(buildPath):
            with open(buildPath, mode="r", encoding="utf-8") as rf:
                lines = rf.readlines()
            for line in lines:
                # 查找不以双斜杠开头的module，即注释了的不要
                if not re.match('^//',line.lstrip()):
                    if re.findall("com.android.application",line.lstrip()):
                        modulePathList.append(os.path.join(modulePath, "src"))
                        break
    return modulePathList


# 删除文件夹
def delModuleFlavors(filePath):
    print("正在删除文件[{0}]".format(filePath))
    shutil.rmtree(filePath)



# 获取项目根路径 D:\project\workspace\TestWorkSpace\NVMS2_REVIEW\NVMS_3.0_Home\NVMS_3.0
_rootPath = sys.argv[1]

# 确定当前是否是组件模式
isModule = isModule(_rootPath)
print("isModule = [{0}]".format(isModule))



# 获取当前项目由多少个module组成
_modList = get_module_name(_rootPath)

# 获取当前项目作为apply plugin: 'com.android.application' 的所有module的全路径，例如 D:\project\workspace\TestWorkSpace\NVMS2_REVIEW\NVMS_3.0_Home\NVMS_3.0\b-tvt-portal\src
moduleList = scanModuleList(_rootPath,_modList)

flavors = os.path.join(_rootPath, "flavors")

# 删除文件夹
for fileName in os.listdir(flavors):
    for modulePath in moduleList:
        path = os.path.join(modulePath,fileName)
        if os.path.exists(path):
            delModuleFlavors(path)

