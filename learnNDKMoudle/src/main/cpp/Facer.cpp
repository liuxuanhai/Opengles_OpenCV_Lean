
//--->[app/src/main/cpp/Facer.cpp]----
// Created by 张风捷特烈 on 2019/10/3.
//

#include "Facer.h"

Facer::Facer(
        const string &top,
        const string &bottom,
        const string &brow,
        const string &eyes) : top(top),
                              bottom(bottom),
                              brow(brow),
                              eyes(eyes) {}

void Facer::printFace() {
    cout<< getFace() << endl;
}

Facer::~Facer() {

}

string Facer::getFace() {
    string result;
    for (int i = 0; i < 10; ++i) {
        i != 9 ? result+=top : result+=top+"\n";
    }
    result+= "|  " +brow + "   " + brow + " |" +"\n";
    result+= "|  " +eyes + "   " + eyes + " |" +"\n";
    result+= "|    -}    |\n";
    for (int i = 0; i < 10; ++i) {
        i != 9 ? result+=bottom : result+=bottom+"\n";
    }
    return result;
}
