#include <iostream>
#include <iomanip>
using namespace std;

int main(){
    cout<<fixed<<setprecision(1);

    double nums[2][4];

    for(int i{0};i<2;i++){
        for(int j{0};j<4;j++){
            cin>>nums[i][j];
        }
    }

    for(int i{0};i<2;i++){
        double sum{0};
        for(int j{0};j<4;j++){
            sum+=nums[i][j];
        }
        cout<<sum/4<<' ';
    }
   cout<<'\n';

    for(int i{0};i<4;i++){
        double sum{0};
        for(int j{0};j<2;j++){
            sum+=nums[j][i];
        }
        cout<<sum/2<<' ';
    }
   cout<<'\n';

    double sum{0};
    for(int i{0};i<2;i++){
        for(int j{0};j<4;j++){
            sum+=nums[i][j];
        }
    }
    cout<<sum/8;
}