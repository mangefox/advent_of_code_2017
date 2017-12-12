import java.io.File

fun part1(lines: List<String>) =
        lines.flatMap { Regex("[a-z]+").findAll(it).toList() .map { it.value } }
             .groupingBy { it }.eachCount()
             .filter { it.value == 1 }

val input = File("input/day_07").readLines()
println("solution 1: " + part1(input))

// part 2
data class Node(val name: String, val weight: Int, val edges: MutableList<Node>) {

    fun sumChildWeights(): Int {
        if (edges.isEmpty()) return weight
        if (edges.map { it.sumChildWeights() }.toSet().size != 1) {
            val sortedNodes = edges.sortedBy { it.sumChildWeights() }
            val delta = sortedNodes.last().sumChildWeights() - sortedNodes.first().sumChildWeights()
            throw IllegalStateException("${sortedNodes.last().name} should be ${sortedNodes.last().weight-delta}")
        }
        return weight + edges.sumBy { it.sumChildWeights() }
    }
}

// create nodes in node map
val nodes = mutableMapOf<String, Node>()
input.map { Regex("(\\w+) \\((\\d+)\\)").find(it)!!.groupValues.drop(1) }
     .map { Node(name = it[0], weight = it[1].toInt(), edges = mutableListOf()) }
     .forEach { nodes[it.name] = it }

// add node edges
for (line in input) {
    val z = Regex("([a-z]+)").findAll(line).map { it.value }.toList()
    if (z.size > 1) {
        for (i in 1..z.lastIndex) {
            nodes[z[0]]?.edges?.add(nodes[z[i]]!!)
        }
    }
}

// solution 2
nodes["fbgguv"]?.sumChildWeights()
