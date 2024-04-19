#include <iostream>

extern "C" int _fastcall MyProc1(long long x, long long y);

int main()
{
	int x = 3, y = 4, z = 0;
	z = MyProc1(x, y);
	return 0;

}
