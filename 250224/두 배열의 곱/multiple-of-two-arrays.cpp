#include <iostream>
#include <vector>
using namespace std;

int SIZE = 3;

int main(){
    vector<vector<int>> v1(SIZE,vector<int>(SIZE));
    vector<vector<int>> v2(SIZE,vector<int>(SIZE));
    
    for(int i{0};i<SIZE;i++){
        for(int j{0};j<SIZE;j++){
            cin>>v1[i][j];
        }   
    }

    for(int i{0};i<SIZE;i++){
        for(int j{0};j<SIZE;j++){
            cin>>v2[i][j];
        }   
    }

    for(int i{0};i<SIZE;i++){
        for(int j{0};j<SIZE;j++){
            cout<< v1[i][j] * v2[i][j]<<' ';
        }   
        cout<<'\n';
    }

}