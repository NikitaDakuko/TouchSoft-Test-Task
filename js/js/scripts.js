function addWeight(val) {

    var errorMessage = document.getElementById('errorMessage');
    errorMessage.style.visibility = "hidden";

    if (val > 20){
        errorMessage.innerHTML = "блин не может весить более 20 фунтов";
        errorMessage.style.visibility = "visible";
    }
    else {
        if (val < 0){
            errorMessage.innerHTML = "блин не может иметь отрицательный вес";
            errorMessage.style.visibility = "visible";
        }
        else {
            var row = document.getElementById('weightsRow');
            var cell = row.insertCell(0);
            cell.setAttribute ("onclick", "toCrossbar(1, this);");
            cell.setAttribute ("oncontextmenu", "toCrossbar(3, this);");
            cell.innerHTML = val;
        }
    }
}

function toCrossbar(n, cell){
    var row;
    if (n == 1) {
        row = document.getElementById('lWeightsRow');
    }
        
    if (n == 3) {
        row = document.getElementById('rWeightsRow');
    }    

    var c = row.insertCell(0);
        c.setAttribute ("onclick", "fromCrossbar(this);");
        c.innerHTML = cell.innerHTML;
    cell.parentElement.deleteCell(cell.cellIndex);
    isBalanced();
}

function fromCrossbar(cell) {
    addWeight(cell.innerHTML);
    cell.parentElement.deleteCell(cell.cellIndex);
    isBalanced();
}

function isBalanced(){
    var lRow = document.getElementById('lWeightsRow'),
        rRow = document.getElementById('rWeightsRow'),
        lSum = 0,
        rSum = 0;
    
    for (let i = 0; i < lRow.cells.length; i++) {
        lSum += parseInt(lRow.cells[i].innerHTML);
    }

    for (let i = 0; i < rRow.cells.length; i++) {
        rSum += parseInt(rRow.cells[i].innerHTML);
    }

    console.log("lSum = " + lSum);
    console.log("rSum = " + rSum);

    if (lSum == rSum){
        document.getElementById('balance').style.visibility = "visible";
    }
    else{
        document.getElementById('balance').style.visibility = "hidden";
    }
}