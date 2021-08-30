OPENQASM 2.0;
include "qelib1.inc";

qreg q[2];
creg c[2];

reset q[0];
h q[0];
reset q[1];
cx q[0],q[1];
measure q[0] -> c[0];