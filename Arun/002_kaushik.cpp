#include <iostream>
#include <cstdio>
#include <queue>
#include <deque>
#include <stack>
#include <algorithm>
#include <cstdlib>
#include <utility>
#include <fstream>
#include <set>
#include <map>
#include <cstring>
#include <cmath>
#include <string>
#include <vector>
#define inf 2e9
using namespace std;


int main(){
  int n;
  cin >> n;
  std::vector<int> ratings(n);

  for(int i = 0; i<n; i++)
    cin >> ratings[i];

  std::vector<int> forward(n,1), backward(n,1);

  for(int i = 1; i<n; i++){
    if(ratings[i] > ratings[i-1])
      forward[i] += forward[i-1];
  }

  for(int i = n-2; i>=0; i--){
    if(ratings[i] > ratings[i+1])
      backward[i] += backward[i+1];
  }

  long long sum = 0;

  for(int i = 0; i<n; i++)
    sum += max(forward[i], backward[i]);

  cout << sum << endl;
}
