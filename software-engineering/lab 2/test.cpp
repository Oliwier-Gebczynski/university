#include "pch.h"

class MultiplicationByIntTests : public ::testing::TestWithParam<tuple<Matrix, int, Matrix>> {

};
TEST_P(MultiplicationByIntTests, CheckMultiplication)
{
	Matrix A = get<0>(GetParam());
	int multiplier = get<1>(GetParam());
	Matrix expected_result = get<2>(GetParam());
	A = A * multiplier;
	ASSERT_TRUE(A == expected_result) << "multiplier: " << multiplier;
}

INSTANTIATE_TEST_CASE_P(MultiplicationByIntTest, MultiplicationByIntTests,
	::testing::Values(
		make_tuple(Matrix(2, 1.0), 2, Matrix(2, 2.0)),
		make_tuple(Matrix(5,3, 1.0), 2, Matrix(5,3, 2.0)),
		make_tuple(Matrix(5, 1.0), 10, Matrix(5, 10.0)),
		make_tuple(Matrix(5, 1.0), 0, Matrix(5, 0.0)),
		make_tuple(Matrix(5, 1.0), -1, Matrix(5, -1.0)),
		make_tuple(Matrix(2, 1.0), 4, Matrix(2, 4.0)),
		make_tuple(Matrix(2, 2.0), 2, Matrix(2, 4.0)),
		make_tuple(Matrix(2, 3.0), 2, Matrix(2, 6.0)),

		//Sprawdzenie czy moze zwrocic wynik w postaci ułamka (jesli w matrix jest ułamek i przemnożymy to przez int i wynikiem jest ułamek to jest błąd)
		make_tuple(Matrix(2, 1.75), 3, Matrix(2, 3.5))
	
	));

// Sprawdzanie dodawania 
class Adding : public ::testing::TestWithParam<tuple<Matrix, Matrix, Matrix>> {

};
TEST_P(Adding, AplusB) {
	Matrix A = get<0>(GetParam());
	Matrix B = get<1>(GetParam());
	Matrix EXP = get<2>(GetParam());

	if (A.rows == B.rows && A.columns == B.columns) {
		EXPECT_NO_THROW({ A + B; });
		Matrix X = A + B;
		EXPECT_TRUE(X == EXP);
	}
	else {
		EXPECT_ANY_THROW({ A + B; });
	}
}

INSTANTIATE_TEST_CASE_P(AddingTest, Adding,
	::testing::Values(
		make_tuple(Matrix(2, 1.0), Matrix(2, 1.0), Matrix(2, 2.0)),
		make_tuple(Matrix(2, 1), Matrix(2, 1), Matrix(2, 1)),
		make_tuple(Matrix(2, 0.0), Matrix(2, 0.0), Matrix(2, 2)),
		make_tuple(Matrix(2, -1.0), Matrix(2, 1.0), Matrix(2, 0.0)),

		//Nie dziala na ułamkach, chyba ze dodawnie ulamkow zwroci liczbe calkowita
		make_tuple(Matrix(2, 1.5), Matrix(2, 1.0), Matrix(2, 2.5)),
		make_tuple(Matrix(2, 1.5), Matrix(2, 1.5), Matrix(2, 3.0))
	));

//Sprawdzanie mnożenia
class MultiplicationByMatrixTests : public ::testing::TestWithParam<tuple<Matrix, Matrix, Matrix>> {

};
TEST_P(MultiplicationByMatrixTests, MMatrix) {
	Matrix A = get<0>(GetParam());
	Matrix B = get<1>(GetParam());
	Matrix EXP = get<2>(GetParam());

	if (A.columns == B.rows) {
		EXPECT_NO_THROW({ A * B; });
		Matrix X = A * B;
		EXPECT_TRUE(X == EXP);
	}
	else {
		EXPECT_ANY_THROW({ A * B; });
	}
}

INSTANTIATE_TEST_CASE_P(MultiplicationTest, MultiplicationByMatrixTests,
	::testing::Values(
		make_tuple(Matrix(2, 2), Matrix(2, 2), Matrix(2, 2)),
		make_tuple(Matrix(2, 1.0), Matrix(2, 2.0), Matrix(2, 4.0)),

		//sprawdzenie mnozenia o roznych rozmiarach
		make_tuple(Matrix(2, 2), Matrix(2, 3), Matrix(2, 3)),
		make_tuple(Matrix(2, 2, 1.0), Matrix(2, 3, 2.0), Matrix(2, 3, 4.0)),
		
		//sprawdzenie z zlym rozmiarem
		make_tuple(Matrix(2, 2), Matrix(3, 2), Matrix(2, 3)),
		make_tuple(Matrix(2, 1.0), Matrix(3, 2.0), Matrix(3, 4.0)),

		//sprawdzenie działania na ułamkach (ponownie jak w dodawaniu nie działa jeśli wynik nie zwraca liczby całkowitej)
		make_tuple(Matrix(2, 1.5), Matrix(2, 1.5), Matrix(2, 4.5)),
		make_tuple(Matrix(2, 1.5), Matrix(2, 2.0), Matrix(2, 6.0))
	));

//Sprawdzanie wyznaczników

//data sety 
std::vector<double> dataTest1 = { 1.5, 1.5, 1.5, 0 }; // wynik = -2.25
std::vector<double> dataTest2 = { 1, 0.5, 5, 10 }; //wynik = 7.5

class DeterminantOfMatrixTest : public ::testing::TestWithParam<tuple<Matrix, double>> {

};
TEST_P(DeterminantOfMatrixTest, DeterminantMatrix) {
	Matrix A = get<0>(GetParam());
	double EXP = get<1>(GetParam());

	if (A.columns == A.rows) {
		EXPECT_TRUE(A.determinant() == EXP);
	}
	else {
		EXPECT_ANY_THROW(A.determinant());
	}
}

INSTANTIATE_TEST_CASE_P(DeterminantTest, DeterminantOfMatrixTest,
	::testing::Values(
		make_tuple(Matrix(2, 2, 3.0), 0),
		make_tuple(Matrix(2, 2), 0),

		//test dla podania zlego wymiaru (pokazuje bład tak jak powinno)
		make_tuple(Matrix(2, 3), 0),

		//test dla wyniku w postaci ułamka i ujemnej wartosci
		make_tuple(Matrix(2, 2, dataTest1), -2.25),

		//test dla wyniku w postaci ułamka dodatniego
		make_tuple(Matrix(2, 2, dataTest2), 7.5),

		//test dla jednowymiarowej (nie dziala dla jednowymiarowych, chyba ze wartosc matrixa jest rowna 0)
		make_tuple(Matrix(1, 1), 0.0),
		make_tuple(Matrix(1, 1, 1.0), 1.0),
		make_tuple(Matrix(1, 1, 3.5), 3.5),
		make_tuple(Matrix(1, 1, -3), -3)

	));

//Sprawdzanie rzędu 

//data sety 
std::vector<double> dataTest3 = { 4, 5, 6, 7, 9, 7, 3, 4, 2 }; // wynik = 3
std::vector<double> dataTest4 = { 4, 4, 2, 2 }; //wynik = 1

class RankOfMatrixTest : public ::testing::TestWithParam<tuple<Matrix, int>> {

};
TEST_P(RankOfMatrixTest, RankMatrix) {
	Matrix A = get<0>(GetParam());
	int EXP = get<1>(GetParam());

	EXPECT_TRUE(A.rank() == EXP);
}

INSTANTIATE_TEST_CASE_P(RankTest, RankOfMatrixTest,
	::testing::Values(
		//sprawdzanie dla macierzy kwadratowych z taka sama waroscia w calosci (mozna zauwazyc tu blad powniewaz poprawnym wynikiem jest 1 a funckja zwraca zero) 
		// (mozliwe ze w funckji rank dla matrix kwadratowych z tymi samymi wartosciami jest sztywno napisane ze ma zwracac 0) 
		make_tuple(Matrix(2, 2, 3.0), 1),
		make_tuple(Matrix(2, 2, 3.0), 0),
		make_tuple(Matrix(2, 2, 5.0), 1),
		make_tuple(Matrix(2, 2, 5.0), 0),

		//test dla matrix ktorej nie mozna uproscic i dla takiej ktora sie da uproscic
		make_tuple(Matrix(3, 3, dataTest3), 3),
		make_tuple(Matrix(2, 2, dataTest4), 1)

	));


//Sprawdzanie ładowania danych

class LoadMatrixTest : public ::testing::TestWithParam<tuple<Matrix, int>> {

};
TEST_P(RankOfMatrixTest, RankMatrix) {
	Matrix A = get<0>(GetParam());
	int EXP = get<1>(GetParam());

	EXPECT_TRUE(A.rank() == EXP);
}

INSTANTIATE_TEST_CASE_P(RankTest, RankOfMatrixTest,
	::testing::Values(
		//sprawdzanie dla macierzy kwadratowych z taka sama waroscia w calosci (mozna zauwazyc tu blad powniewaz poprawnym wynikiem jest 1 a funckja zwraca zero) 
		// (mozliwe ze w funckji rank dla matrix kwadratowych z tymi samymi wartosciami jest sztywno napisane ze ma zwracac 0) 
		make_tuple(Matrix(2, 2, 3.0), 1),
		make_tuple(Matrix(2, 2, 3.0), 0),
		make_tuple(Matrix(2, 2, 5.0), 1),
		make_tuple(Matrix(2, 2, 5.0), 0),

		//test dla matrix ktorej nie mozna uproscic i dla takiej ktora sie da uproscic
		make_tuple(Matrix(3, 3, dataTest3), 3),
		make_tuple(Matrix(2, 2, dataTest4), 1)

	));