<#assign content>

<h1> Stars </h1>

<div id="nearest" class="left"> Nearest Neighbors Search <br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nearest-switch" type="checkbox">
    <span class="slider round"></span>
  </label>
  <label for="text">Star Name</label> <br>
<form method="GET" action="/neighbors" class="left">
  <label for="text">Enter query here: </label><br>
  <input id="nearest-input" name="text" id="text"> <br>
  <button id="compute-nearest"> search </button>
</form>
</div>

<div class="right"> Radius Search <br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input type="checkbox">
    <span class="slider round"></span>
  </label>
  <label for="text">Star Name</label> <br>
<form method="GET" action="/radius" class="right">
  <label for="text">Enter query here: </label><br>
  <input id="radius-input" name="text" id="text"> <br>
  <button id="compute-radius"> search </button>
</form>
</div>

  <br>
  <br>
  <br>
  <br>
  <br>

<h1> Search Results </h1>
  ${results}

</#assign>
<#include "main.ftl">