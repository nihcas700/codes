#include <iostream>
#include <cstring>

using namespace std;

int main(){
  char str[100050];
  int res = 0;
  int char_occurance[260];
  cin >> str;
  int l = strlen(str);

  for(int i = 0; i < l; ){
    memset(char_occurance, 0, sizeof(char_occurance));
    int count = 0, j;
    for(j = i; j < l && char_occurance[str[j] - 'a'] == 0; j++){
      char_occurance[str[j] - 'a'] = 1;
      count++;
    }
    res = max(res, count);
    i = j;
  }
  cout << res << endl;
  return 0;
}