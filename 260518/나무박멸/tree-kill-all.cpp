#include <iostream>
#include <vector>
#include<iomanip>
using namespace std;

int dx[8] = { 0,1,0,-1,1,1,-1,-1 }; 
int dy[8] = { 1,0,-1,0,1,-1,1,-1 };

int N, M, K, C; // 격자 크기, 박멸 진행되는 년 수, 제초제 확산범위, 제초제 남아있는 년 수 

vector<vector<int>> map; // 양수: 나무, 0: 아무것도 없음, 음수: 벽 or 제초제 
vector<vector<int>> herbicide_time; 

void step1();
void step2();
void step3(); 
void print(); 

int total_kill_cnt{ 0 }; 
int year{ 1 }; 

int main() { 
    ios::sync_with_stdio(false); 
    cin.tie(0); 
    
    cin >> N >> M >> K >> C; 
    map.resize(N, vector<int>(N)); 
    herbicide_time.resize(N, vector<int>(N)); 
    for (int i = 0; i < N; i++) { 
        for (int j = 0; j < N; j++) { 
            cin >> map[i][j]; 
        } 
    } 
    
    //cout << '\n';   //***************
    
    for (year = 1; year <= M; year++) { 
        step1(); 
        // print(); //***************
        step2(); 
        // print(); //***************
        step3(); 
        // print(); //***************

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (herbicide_time[i][j] > 0) {
                    herbicide_time[i][j]--;
                }
            }
        }
    } 
    
    cout << total_kill_cnt; 
} 

// 인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장
void step1() { 
    for (int i = 0; i < N; i++) { 
        for (int j = 0; j < N; j++) { 
            
            int cnt{ 0 }; 
            for (int d = 0; d < 4; d++) { 
                int nx = i + dx[d]; 
                int ny = j + dy[d]; 
                
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; 
                
                if (map[nx][ny] > 0) cnt++; 
            } 
            
            if(map[i][j] > 0) 
                map[i][j] = map[i][j] + cnt; 
        } 
    } 
} 

/*
기존에 있었던 나무들은 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식
각 칸의 나무 그루 수에서 총 번식이 가능한 칸의 개수만큼 나누어진 그루 수만큼 번식
*/
void step2() { 
    vector<vector<int>> clone_map = map; 
    
    for (int i = 0; i < N; i++) { 
        for (int j = 0; j < N; j++) { 
            if (clone_map[i][j] > 0) { 
                int cnt{ 0 }; 
                
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d]; 
                    int ny = j + dy[d]; 
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    
                    if (clone_map[nx][ny] == 0 && herbicide_time[nx][ny] == 0) cnt++; 
                } 

                if (cnt == 0)continue;
                
                for (int d = 0; d < 4; d++) { 
                    int nx = i + dx[d]; 
                    int ny = j + dy[d]; 
                    
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue; 
                    
                    if (clone_map[nx][ny] == 0 && herbicide_time[nx][ny] == 0) {
                        map[nx][ny] += map[i][j] / cnt; 
                    } 
                } 

                //cout << i << ", " << j << '\n';  //***************
                //print();  //***************
            }
        } 
    } 
} 

/*
각 칸 중 제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제를 뿌린다
제초제는 k범위만큼 대각선으로 퍼진다
벽이 있으면 가로막혀 전파되지 않음
제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가 c+1년째가 될 때 사라진다
*/
void step3() { 
    int max_kill{ 0 }; 
    pair<int, int> max_idx = { 0,0 }; 
    
    for (int i = 0; i < N; i++) { 
        for (int j = 0; j < N; j++) { 
            if (map[i][j] <= 0) continue;

            int cnt{ 0 }; 
            cnt += map[i][j];
            for (int d{ 4 }; d < 8; d++) { 
                for (int k{ 1 }; k <= K; k++) { 
                    int nx = i + dx[d] * k; 
                    int ny = j + dy[d] * k; 

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) break; 
                    if (map[nx][ny] == -1 || map[nx][ny] == 0) break;

                    if(map[nx][ny] > 0) cnt += map[nx][ny];
                } 
            }

            // cout << i << ", " << j << ": " << cnt << '\n';

            if (max_kill < cnt) { 
                max_kill = cnt; 
                max_idx = { i,j }; 
            } 
        }
    }
    
    int x = max_idx.first; 
    int y = max_idx.second; 

    herbicide_time[x][y] = C + 1;
    map[x][y] = 0; 
    
    total_kill_cnt += max_kill;
    for (int d{ 4 }; d < 8; d++) { 
        for (int k{ 1 }; k <= K; k++) { 
            int nx = x + dx[d] * k; 
            int ny = y + dy[d] * k; 
            
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) break; 
            
            herbicide_time[nx][ny] = C + 1; 
            if (map[nx][ny] == -1 || map[nx][ny] == 0) break;

            map[nx][ny] = 0;
        } 
    } 
}

void print() {
    for (int i = 0; i < N; i++) { 
        for (int j = 0; j < N; j++) {
            cout << setw(3) << map[i][j];
        }
        cout << '\n'; 
    }cout << '\n'; 
}