#include <iostream>
#include <vector>
using namespace std;

int main() {
    int N, M;
    cin>>N>>M;

    vector<vector<int>> A(N, vector<int>(M));
    for(int i{0};i<N;i++){
        for(int j{0};j<M;j++){
            cin>>A[i][j];
        }
    }

    vector<vector<int>> result(N, vector<int>(M));
    for(int i{0};i<N;i++){
        for(int j{0};j<M;j++){
            int num;
            cin>>num;
            if(num!=A[i][j]){
                result[i][j]=1;
            }
        }
    }

    for(vector<int> row: result){
        for(int num:row){
            cout<<num<<' ';
        }
        cout<<'\n';
    }

    return 0;
}