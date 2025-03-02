#include <iostream>
using namespace std;

int main() {
    int n;
    cin >> n;
    
    int arr[100][100];
    int num = 1;
    
    for (int col = 0; col < n; col++) {
        for (int row = 0; row < n; row++) {
            arr[row][col] = num++;
        }
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << arr[i][j] << " ";
        }
        cout << "\n";
    }
    
    return 0;
}
