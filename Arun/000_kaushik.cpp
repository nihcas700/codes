#include <iostream>
#include <vector>

using namespace std;

int main(){
  int n, m, l, r;
  int ans = 0;
  int moods[110];
  std::vector<pair<int, int> > subarrs;
  
  cin >> n >> m;

  for(int i = 1; i <= n; i++)
    cin >> moods[i];

  for(int i = 1; i <= m; i++){
    cin >> l >> r;
    int count = 0;
    for(int k = l; k <= r; k++)
      count += moods[k];

    if(count > 0)
      subarrs.push_back(make_pair(l,r));
  }

  for(int i = 0; i < subarrs.size(); i++){
    for(int j = subarrs[i].first; j <= subarrs[i].second; j++){
      ans += moods[j];
    }
  }

  cout << ans << endl;
}