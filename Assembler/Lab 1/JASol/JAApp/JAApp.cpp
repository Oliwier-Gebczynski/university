#include <iostream>

extern "C" int _fastcall MyProc1(long long x, long long y);
extern "C" int _fastcall MyProc2(long long x, long long y);

int main()
{
	int x = 3, y = 4, z = 0;
	z = MyProc2(x, y);
	return 0;

}
