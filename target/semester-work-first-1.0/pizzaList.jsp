<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
  /*.card-img-top{*/
  /*width: 45%;*/
  /*text-align: center;*/
  /*}*/
  * {
    box-sizing: border-box;
  }
  .fXKtar {
    display: flex;
    flex-flow: column;
    -webkit-box-pack: justify;
    justify-content: space-around;
    margin-bottom: 60px;
    cursor: pointer;
    width: 292px;
    margin-right: 37.3333px;
  }
  .jDJosZ {
    margin: 0px;
    width: 100%;
    font-size: 14px;
    line-height: 20px;
    font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    color: rgb(92, 99, 112);
    font-weight: 400;
  }
  main {
    display: block;
  }
  .fIPpHH {
    margin-top: 16px;
    display: flex;
  }
  .dMgSaw {
    margin: 0px;
    position: relative;
    width: 100%;
    border-radius: 12px;
    top: 0px;
    transition: top 150ms ease-out 0s;
  }
  .kAffkg {
    width: 100%;
    position: relative;
    user-select: none;
    image-rendering: auto;
    display: flex;
    flex-flow: column;
  }
  .jbQjVh {
    margin: 8px 0px;
    color: rgb(0, 0, 0);
    font-size: 20px;
    line-height: 24px;
    font-family: Dodo, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-weight: 500;
  }
  .fIPpHH {
    margin-top: 16px;
    display: flex;
  }
  .fIPpHH .product-control-price {
    flex: 1 1 auto;
    padding-right: 4px;
    font-size: 20px;
    color: rgb(0, 0, 0);
    font-family: Dodo, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-weight: 600;
    line-height: 22px;
    display: flex;
    flex-flow: column;
    -webkit-box-pack: center;
    justify-content: center;
  }
  .fIPpHH .product-control {
    flex: 0 0 auto;
    height: 40px;
    min-width: 120px;
  }
  .jFZvXc[data-size="medium"] {
    height: 40px;
    padding: 8px 20px;
    font-size: 16px;
    line-height: 24px;
  }
  .jFZvXc[data-type="secondary"] {
    background-color: rgb(255, 240, 230);
    color: rgb(209, 87, 0);
  }
  .jFZvXc {
    outline: none;
    border: none;
    border-radius: 9999px;
    text-align: center;
    font-family: Dodo, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-weight: 500;
    text-decoration: none;
    position: relative;
    overflow: hidden;
    cursor: pointer;
    user-select: none;
    transition-property: background-color, color;
    transition-duration: 200ms;
    transition-timing-function: ease-out;
  }
  button {
    appearance: auto;
    writing-mode: horizontal-tb !important;
    font-style: ;
    font-variant-ligatures: ;
    font-variant-caps: ;
    font-variant-numeric: ;
    font-variant-east-asian: ;
    font-weight: ;
    font-stretch: ;
    font-size: ;
    font-family: ;
    text-rendering: auto;
    color: buttontext;
    letter-spacing: normal;
    word-spacing: normal;
    line-height: normal;
    text-transform: none;
    text-indent: 0px;
    text-shadow: none;
    display: inline-block;
    text-align: center;
    align-items: flex-start;
    cursor: default;
    box-sizing: border-box;
    background-color: buttonface;
    margin: 0em;
    padding: 1px 6px;
    border-width: 2px;
    border-style: outset;
    /*border-color: buttonborder;*/
    border-image: initial;
  }
  .fIPpHH {
    margin-top: 16px;
    display: flex;
  }
  .cgxKgX {
    width: 100%;
    color: rgb(0, 0, 0);
    font-family: Dodo, system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-weight: 600;
    font-size: 36px;
    margin: 32px 0px;
    margin-left: 300px;
  }
  html {
    height: 100%;
    margin: 0px;
    padding: 0px;
    font: inherit;
    vertical-align: baseline;
    background: rgb(255, 255, 255);
  }
  h2 {
    display: block;
    font-size: 1.5em;
    margin-block-start: 0.83em;
    margin-block-end: 0.83em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
  }
  .bxiXBh {
    display: flex;
    flex-wrap: wrap;
  }
  .ctZmFE {
    width: 1280px;
    margin-left: auto;
    margin-right: auto;
  }
  .cLvPrd {
    flex: 1 0 auto;
    /*margin: 0 5px;*/
  }
  .pizza-card{
    flex: 1 1 auto;
    margin: 0 5px;
  }


</style>
<t:mainLayout title="Pizza list">
  
  <h2 class="sc-1n2d0ov-0 cgxKgX">своя Пицца</h2>


  <main class="sc-1bt2sgz-2 sc-1n2d0ov-1 cLvPrd ctZmFE">
    <section id="pizzas" class="sc-1n2d0ov-2 bxiXBh">

    <c:forEach var="pizza" items="${pizzas}">
      <div class="pizza-card">

          <main class="main-info">
            <article data-testid="menu__meta-product_11ED220E823D778CED54FA474E6B6120" class="sc-1tpn8pe-3 fXKtar">
              <main class="sc-1tpn8pe-0 jDJosZ">
              <picture class="sc-1js33uh-0 kAffkg sc-1tpn8pe-4 dMgSaw" data-type="1">
                <img alt="${pizza.getPizza_name()}"  class="img" src="storage/${pizza.getPhotoPath()}"></picture>
              <div data-gtm-id="${pizza.getPizza_name()}" class="sc-1tpn8pe-1 jbQjVh">${pizza.getPizza_name()}</div>
                  ${pizza.getIngredients()}
              </main>
              <footer class="sc-1tpn8pe-2 fIPpHH">
                <div class="product-control-price">${pizza.getPrice()} ₽</div>
                <button data-testid="product__button" type="button" data-type="secondary" data-size="medium" class="sc-1rmt3mq-0 jFZvXc product-control">
                  <c:if test="${user.getRole() == 'admin'}">
                    <a href="update-pizza?id=${pizza.getId()}" >Обновить</a>
                    <a href="DeletePizzaServlet?id=${pizza.getId()}" >Удалить</a>
                  </c:if>
                  <c:if test="${user.getRole() == null}">
                    <a href="add-to-cart?id=${pizza.id}" >Выбрать</a>
                  </c:if>
                </button>
              </footer>
            </article>

      </div>

    </c:forEach>
    </section>
  </main>
</t:mainLayout>