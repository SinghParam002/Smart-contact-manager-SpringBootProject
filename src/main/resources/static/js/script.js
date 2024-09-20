console.log("file")

let currenttheme =getTheme();
changetheme();

function changetheme(){
console.log(currenttheme)

}

function settheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    let theme = localStorage.getItem("theme");
   return theme ? theme:"light"
}