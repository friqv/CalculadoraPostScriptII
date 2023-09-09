import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Stack;

public class PostScript {
    private Stack<Double> pila;
    private static final Logger logger = LoggerFactory.getLogger(PostScript.class);

    public PostScript() {
        pila = new Stack<>();
    }

    public double ejecutar(String script) {
        String[] tokens = script.split("\\s+");

        for (String token : tokens) {
            if (esNumero(token)) {
                pila.push(Double.parseDouble(token));
            } else if (token.equals("+")) {
                if (pila.size() >= 2) {
                    double b = pila.pop();
                    double a = pila.pop();
                    pila.push(a + b);
                } else {
                    logger.error("Error, los Operandos son insuficientes para la operación 'add'.");
                    return 0.0;
                }
            } else if (token.equals("-")) {
                if (pila.size() >= 2) {
                    double b = pila.pop();
                    double a = pila.pop();
                    pila.push(a - b);
                } else {
                    logger.error("Error, los  Operandos  son insuficientes para la operación 'sub'.");
                    return 0.0;
                }
            } else if (token.equals("*")) {
                if (pila.size() >= 2) {
                    double b = pila.pop();
                    double a = pila.pop();
                    pila.push(a * b);
                } else {
                    logger.error("Error: Operandos insuficientes para la operación 'mul'.");
                    return 0.0;
                }
            } else if (token.equals("/")) {
                if (pila.size() >= 2) {
                    double b = pila.pop();
                    double a = pila.pop();
                    if (b != 0) {
                        pila.push(a / b);
                    } else {
                        logger.error("Error: División por cero.");
                        return 0.0;
                    }
                } else {
                    logger.error("ERROR EN LA DIV'.");
                    return 0.0;
                }
            } else if (token.equals("^")) {
                if (pila.size() >= 2) {
                    double exponente = pila.pop();
                    double base = pila.pop();
                    double resultado = Math.pow(base, exponente);
                    pila.push(resultado);
                } else {
                    logger.error("ERRORSOTE '^'.");
                    return 0.0;
                }
            } else if (token.equals("sqrt")) {
                if (!pila.isEmpty()) {
                    double operando = pila.pop();
                    if (operando >= 0) {
                        double resultado = Math.sqrt(operando);
                        pila.push(resultado);
                    } else {
                        logger.error("ESTA IMPOSIBLE ES OPERACION DE NUMERO NEGATIVO");
                        return 0.0;
                    }
                } else {
                    logger.error("ERROR EN LA RAIZ'.");
                    return 0.0;
                }
            } else {
                logger.error("Error: Token no reconocido: " + token);
                return 0.0;
            }
        }

        if (!pila.isEmpty()) {
            return pila.pop();
        } else {
            return 0.0;
        }
    }

    private boolean esNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}