class PID{
public:
    PID(double p, double i, double d, double max_integral, double min_integral);

    void update(int measured, int setpoint, int* process_variable);
private:
    double p, i, d, max_integral, min_integral, prev_error, integral;
};
