#pragma once

class ActivateFucntion {
public:
	virtual double exec(double input) = 0;

	virtual double derivative(double fx, double x) = 0;
};

class Sigmoid : public ActivateFucntion {
public:
	double exec(double input);
	double derivative(double fx, double x) = 0;
};

class Tanh : public ActivateFucntion {
public:
	double exec(double input);
	double derivative(double fx, double x) = 0;
};

class Arctan : public ActivateFucntion {
public:
	double exec(double input);
	double derivative(double fx, double x) = 0;
};

class Relu : public ActivateFucntion {
public:
	double exec(double input);
	double derivative(double fx, double x) = 0;
};