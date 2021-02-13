package edu.brown.cs.student.stars;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import edu.brown.cs.student.Mock.Mock;
import edu.brown.cs.student.util.REPL;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;
import com.google.common.collect.ImmutableMap;
import freemarker.template.Configuration;


/**
 * The Main class of our project. This is where execution begins.
 *
 */
public final class Main {

  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args
   *          An array of command line arguments
   */
  public static void main(String[] args) throws IOException {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() throws IOException {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
    .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    REPL commandPrompt = new REPL();
    commandPrompt.addCommand(new Stars());
    commandPrompt.addCommand(new NaiveNeighbors());
    commandPrompt.addCommand(new NaiveRadius());
    commandPrompt.addCommand(new Mock());
    commandPrompt.addCommand(new Radius());
    commandPrompt.addCommand(new Neighbors());
    commandPrompt.run();
  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    Spark.port(port);
    Spark.externalStaticFileLocation("src/main/resources/static");
    Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();

    // Setup Spark Routes
    Spark.get("/stars", new FrontHandler(), freeMarker);
    Spark.get("/load", new LoadStarsHandler(), freeMarker);
    Spark.get("/neighbors", new NeighborsHandler(), freeMarker);
    Spark.get("/radius", new RadiusHandler(), freeMarker);
    Spark.get("/edu/brown/cs/student/stars", new FrontHandler(), freeMarker);
  }

  /**
   * Handle requests for Neighbors commands.
   *
   */
  private static class LoadStarsHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) throws Exception {
      Stars s = new Stars();
      QueryParamsMap qm = req.queryMap();
      String path = qm.value("path");
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      s.run(" " + path);
      String resultsString = out.toString();
      Map<String, String> variables = ImmutableMap.of("title", "Loaded", "results", resultsString);
      return new ModelAndView(variables, "query.ftl");
    }
  }

  /**
   * Handle requests to the front page of our Stars website.
   *
   */
  private static class FrontHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title",
          "STARS: Super Tangible And Real Systems", "results", "");
      return new ModelAndView(variables, "query.ftl");
    }
  }

  /**
   * Handle requests for Neighbors commands.
   *
   */
  private static class RadiusHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) throws Exception {
      Radius r = new Radius();
      QueryParamsMap qm = req.queryMap();
      String count = qm.value("radius");
      String x = qm.value("x");
      String y = qm.value("y");
      String z = qm.value("z");
      String name = qm.value("name");
      Star[] results;
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      if (name == null) {
        results = r.run(" " + count + " " + x + " " + y + " " + z);
      } else {
        results = r.run(" " + count + " " + "\"" + name + "\"");
      }
      String replOutput = out.toString();
      String resultsString;
      if (replOutput.contains("ERROR: ")) {
        resultsString = replOutput;
      } else {
        resultsString = "<table>"
            + "<tr>"
            + "<th>Star ID</th>"
            + "<th>Star Name</th>"
            + "<th>X</th>"
            + "<th>Y</th>"
            + "<th>Z</th>"
            + "</tr>";
        for (Star star : results) {
          resultsString = resultsString + "<tr>"
              + "<td>" + star.getStarID() + "</td>"
              + "<td>" + star.getProperName() + "</td>"
              + "<td>" + star.getX() + "</td>"
              + "<td>" + star.getY() + "</td>"
              + "<td>" + star.getZ() + "</td>"
              + "</tr>";
        }
      }
      resultsString = resultsString + "</table>";
      Map<String, String> variables = ImmutableMap.of("title", "RADius Results", "results", resultsString);
      return new ModelAndView(variables, "query.ftl");
    }
  }

  /**
   * Handle requests for Neighbors commands.
   *
   */
  private static class NeighborsHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) throws Exception {
      Neighbors n = new Neighbors();
      QueryParamsMap qm = req.queryMap();
      String count = qm.value("count");
      String x = qm.value("x");
      String y = qm.value("y");
      String z = qm.value("z");
      String name = qm.value("name");
      Star[] results;
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      System.setOut(new java.io.PrintStream(out));
      if (name == null) {
        results = n.run(" " + count + " " + x + " " + y + " " + z);
      } else {
        results = n.run(" " + count + " " + "\"" + name + "\"");
      }
      String replOutput = out.toString();
      String resultsString;
      if (replOutput.contains("ERROR: ")) {
        resultsString = replOutput;
      } else {
        resultsString = "<table>"
            + "<tr>"
            + "<th>Star ID</th>"
            + "<th>Star Name</th>"
            + "<th>X</th>"
            + "<th>Y</th>"
            + "<th>Z</th>"
            + "</tr>";
        for (Star star : results) {
          resultsString = resultsString + "<tr>"
              + "<td>" + star.getStarID() + "</td>"
              + "<td>" + star.getProperName() + "</td>"
              + "<td>" + star.getX() + "</td>"
              + "<td>" + star.getY() + "</td>"
              + "<td>" + star.getZ() + "</td>"
              + "</tr>";
        }
      }
      resultsString = resultsString + "</table>";
      Map<String, String> variables = ImmutableMap.of("title", "Neighbors Results", "results", resultsString);
      return new ModelAndView(variables, "query.ftl");
    }
  }

  /**
   * Display an error page when an exception occurs in the server.
   *
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

}
