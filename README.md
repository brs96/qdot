[![Unitary Fund](https://img.shields.io/badge/Supported%20By-UNITARY%20FUND-brightgreen.svg?style=for-the-badge)](http://unitary.fund)

## Qdot

This project is new and under initial development. 
Qdot is a Scala 3 library (QASM-transpiler) that allows Scala/Java developers 
write native quantum/hybrid programs.

## Motivations
1. Compile-time correctness. This becomes particularly important as size of hybrid programs increases,
Qdot aims to leverage Scala's robust type system for compile time checks.


2. Run hybrid programs on JVM directly, by sending transpiled QASM to quantum computers directly through IBM qiskit python.
Basic classical simulation will be implemented. Optimization will be minimal.


3. Allow concise, expressive and intuitive quantum/hybrid programs by leveraging Scala syntax and it's static type system.


For any question please ask in #qdot channel in http://discord.unitary.fund/


## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

To run written quantum programs on IBMQ, you will need to have Python and Qiskit install
Install qiskit with `pip install qiskit`, use a new separate environment if you wish (e.g `conda create -n ENV_NAME python=3` and `conda activate ENV_NAME`)
You need to provide you IBMQ api token to our `scala.qdot.backend.IBMQBackend.submitQASMToIBMQ` method.
