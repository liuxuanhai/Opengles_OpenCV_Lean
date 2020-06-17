##指定 cmake 的最小版本
#cmake_minimum_required(VERSION 3.4.1)
#
## 使用native-lib.cpp文件生成共享库native-lib
#add_library(native-lib SHARED native-lib.cpp )
#
## 在ndk中查找log库 取别名log-lib
#find_library(log-lib log )
#
##设置 target 需要链接的库
#target_link_libraries(native-lib ${log-lib} )


#指定 cmake 的最小版本
cmake_minimum_required(VERSION 3.4.1)

include_directories(include)#引入文件夹

#编译头文件
#定义全局 my_source_path 变量
file(GLOB my_source_path ${CMAKE_SOURCE_DIR}/*.cpp ${CMAKE_SOURCE_DIR}/*.c)
add_library(tolyCV SHARED ${my_source_path})

#添加动态链接库
add_library(lib_opencv SHARED IMPORTED)
set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/../jniLibs/${ANDROID_ABI}/libopencv_java4.so)

# 在ndk中查找log库 取别名log-lib
find_library(log-lib log)

# 在ndk中查找jnigraphics库 取别名jnigraphics-lib
# jnigraphics包含图形操作的库
find_library(jnigraphics-lib jnigraphics)

#设置 target 需要链接的库
target_link_libraries(
        tolyCV
        lib_opencv
        ${jnigraphics-lib}
        ${log-lib})
