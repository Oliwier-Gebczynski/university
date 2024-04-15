#include "pch.h"
#include "framework.h"
#include "HistogramStretching.h"

void CHistogramStretching::HistogramStretching(BYTE** pImage, int nW, int nH)
{
	//tablica do tablicowania 
	int nValues[256];

	//find minimal value (zamiana "i" z "j", zamiana z dwoch w jeden dodanie else if do najwiekszego [poprawa o 80ms]) 
	int nMin = pImage[0][0];
	int nMax = pImage[0][0];
	for (int i = 0; i < nH; i++) {
		for (int j = 0; j < nW; j++) {
			if (pImage[i][j] < nMin) {
				nMin = pImage[i][j];
			}
			if (pImage[i][j] > nMax) {
				nMax = pImage[i][j];
			}
		}
	}
	//find maximal value
	//int nMax = pImage[0][0];
	//for(int j = 0; j < nW; j++)
	//	for(int i = 0; i < nH; i++)
	//		if(pImage[i][j] > nMax)
	//			nMax = pImage[i][j];

	//normalize the image values
	float fScale = (nMax - nMin) * 0.01; //calculates scale (wyciagniecie zmiennej z petli)
	auto fScaleM = 1 / fScale; // zamiana z dzielenia na mnozenie 

	for (int i = 0; i < 256; i++) {
		float fVal = (i - nMin) * fScaleM;//scales pixel value
		nValues[i] = (int)(fVal + 0.5);//rounds floating point number to integer
	}

	for(int i = 0; i < nH; i++) //zamiana iteratorow
		for(int j = 0; j < nW; j++)
		{
			if(nMax != nMin)
			{
				//checks BYTE range (must be 0-255) (zbedna funkcja sprawdzajaca)
				//if(nVal < 0)
				//	nVal = 0;
				//else if(nVal > 255)
				//	nVal = 255;

				pImage[i][j] = nValues[pImage[i][j]];
			}
			else
				pImage[i][j] = 0;//if all pixel values are the same, the image is changed to black
		}

}

//Your optimization:
//Average: 47.084400
//Std dev : 0.956747
//Minimum : 40.357900
//Maximum : 51.076400

//Before optimization :
//Average: 613.704250
//Std dev : 0.820279
//Minimum : 589.856300
//Maximum : 717.554400

//Reference optimization :
//Average: 50.957870
//Std dev : 0.438744
//Minimum : 46.945700
//Maximum : 52.916200
