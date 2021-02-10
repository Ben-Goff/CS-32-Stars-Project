<#assign content>

<h1> Stars </h1>

<div class="left"> Nearest Neighbors Search <br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="nearest-switch" type="checkbox">
    <span class="slider round"></span>
  </label>
  <label for="text">Star Name</label> <br>
<form class="shown" id="nearest-coords" method="GET" action="/neighbors" class="left">
  <label for="count">count </label><br>
  <input id="count" name="count"> <br>
  <label for="x">x </label><br>
  <input id="x" name="x"> <br>
  <label for="y">y </label><br>
  <input id="y" name="y"> <br>
  <label for="z">z </label><br>
  <input id="z" name="z"> <br>
  <button id="compute-nearest-coords"> search </button>
</form>
<form class="hidden" id="nearest-name" method="GET" action="/neighbors" class="left">
  <label for="count">count </label><br>
  <input id="count" name="count"> <br>
  <label for="name">Star name: </label><br>
  <input id="name" name="name" id="name"> <br>
  <button id="compute-nearest-name"> search </button>
</form>
</div>

<div class="right"> Radius Search <br>
  <label for="text">Coordinates</label>
  <label class="switch">
    <input id="radius-switch" type="checkbox">
    <span class="slider round"></span>
  </label>
  <label for="text">Star Name</label> <br>
  <label for="text">radius </label><br>
  <input id="x" name="text" id="text"> <br>
<form class="shown" id="radius-coords" method="GET" action="/radius" class="left">
  <label for="text">x </label><br>
  <input id="x" name="text" id="text"> <br>
  <label for="text">y </label><br>
  <input id="y" name="text" id="text"> <br>
  <label for="text">z </label><br>
  <input id="z" name="text" id="text"> <br>
  <button id="compute-radius-coords"> search </button>
</form>
<form class="hidden" id="radius-name" method="GET" action="/radius" class="right">
  <label for="text">Enter query here: </label><br>
  <input id="radius-input" name="text" id="text"> <br>
  <button id="compute-radius-name"> search </button>
</form>
</div>

  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>
  <br>

<h1> Search Results </h1>
  ${results}


</#assign>
<#include "main.ftl">