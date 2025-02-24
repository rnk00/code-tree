#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n, m;
    cin>>n>>m;
    vector<vector<int>> nums(n, vector<int>(m));

    for(int i{0};i<n;i++){
        for(int j{0};j<m;j++){
            cout<<i*3+j+1<<' ';
        }
        cout<<'\n';
    }


    return 0;
}