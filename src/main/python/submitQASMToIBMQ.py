from qiskit import QuantumCircuit, transpile, Aer, IBMQ, execute
import sys

def main():
    args = sys.argv[1:]

    provider = IBMQ.enable_account(args[0])
    qc = QuantumCircuit.from_qasm_file("src/main/scala/qdot/gate/BellStateQASM.qasm")
    backend = Aer.get_backend("qasm_simulator")

    job = execute(qc, backend)
    result = job.result()
    print(result.get_counts())


if __name__ == "__main__":
    main()