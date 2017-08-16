package groovy.json

/**
 * Created by I340951 on 8/16/2017.
 */
class DiffResult {
    def key, a_value, b_value
    String toString() {
        "[ key: ${key}, a_value: ${a_value}, b_value: ${b_value} ]"
    }
}
