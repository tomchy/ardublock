#include "pid.h"

PID::PID(double p, double i, double d, double max_integral, double min_integral) {
    this->p = p;
    this->i = i;
    this->d = d;
    this->max_integral = max_integral;
    this->min_integral = min_integral;

    this->prev_error = 0.0;
    this->integral = 0.0;
}

void PID::update(int measured, int setpoint, int* process_variable) {
    double error = (double) (setpoint - measured);
    double derivative = (double) (error - prev_error);
    prev_error = error;
    integral += error;
    integral = (integral>this->min_integral) ? integral : this->min_integral;
    integral = (integral<this->max_integral) ? integral : this->max_integral;
    double out = this->p * error + this->i * integral + this->d * derivative;

    out = (out > this->min_integral) ? out : this->min_integral;
    out = (out < this->max_integral) ? out : this->max_integral;

    *process_variable = (int) out;
}
