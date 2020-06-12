cmake_minimum_required(VERSION 3.4.1)

#定义全局 my_source_path 变量
file(GLOB my_source_path
        ${CMAKE_SOURCE_DIR}/*.cpp
        ${CMAKE_SOURCE_DIR}/*.c)

add_library(toly_facer-lib
        SHARED ${my_source_path})

find_library(log-lib log)

target_link_libraries(toly_facer-lib ${log-lib})
