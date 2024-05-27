#include "Abstract.h"
#include <cmath>

const int beta = 1;

double Sigmoid::exec(double input) {
	return (1 / (1 + exp(-beta * input)));
};

double Sigmoid::derivative(double fx, double x) {
	return beta * (1 - fx) * fx;
};

double Tanh::exec(double input) {
	return tanh(beta * input);
}

double Tanh::derivative(double fx, double x) {
	return beta * (1 - (fx * fx));
}

double Arctan::exec(double input) {
	return atan(input);
};

double Arctan::derivative(double fx, double x) {
	return 1 / ((x * x) + 1);
};

double Relu::exec(double input) {
	if (input < 0) {
		return 0;
	}
	else if (input >= 0){
		return 1;
	}
};

double Relu::derivative(double fx, double x) {
	if (x < 0) {
		return 0;
	}
	else if(x >= 0){
		return 1;
	}
};

