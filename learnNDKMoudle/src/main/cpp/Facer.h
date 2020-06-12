
//--->[app/src/main/cpp/Facer.h]----
// Created by 张风捷特烈 on 2019/10/3.
//

#include <iostream>

using namespace std;

#ifndef TOLYC_FACER_H
#define TOLYC_FACER_H

class Facer {
public:
    Facer(const string &top="#", const string &bottom="#", const string &brow="~", const string &eyes=".");
    ~Facer();
public:
    string top;
    string bottom;
    string brow;
    string eyes;
public:
    void printFace() ;
    string getFace();
};


#endif //TOLYC_FACER_H
