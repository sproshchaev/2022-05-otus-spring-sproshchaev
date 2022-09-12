buttonEnter.onclick = function () {
    let request = new XMLHttpRequest();
    request.open("GET", "/api/authors");
    request.send();
    request.onload = function () {
        if (request.status = 200) {
            createHeaderAuthorsPage();
            createAuthorsTable(request);
       } else {
            alert(`Ошибка ${request.status}: ${request.statusText}`);
        }
    }
}

function createHeaderAuthorsPage() {
    title.innerHTML = "Authors";
    document.getElementById('main_div').innerHTML = '';
    let p = document.createElement('p');
    document.getElementById('main_div').appendChild(p);
    let span = document.createElement('span');
    p.appendChild(span);
    p.innerHTML = 'Authors in the library </br>';
    p.style = 'font-weight: bold';
    let img = document.createElement('img');
    p.appendChild(img);
    img.src = '/library.png';
}

function createAuthorsTable(request) {
    let authorDtoList = JSON.parse(request.response);
    let table = document.createElement('table');
    document.getElementById('main_div').appendChild(table);
    table.classList.add('books');
    let thead = document.createElement('thead');
    let tbody = document.createElement('tbody');
    table.appendChild(thead);
    table.appendChild(tbody);
    let row_0 = document.createElement('tr');
    let heading_1 = document.createElement('th');
    heading_1.innerHTML = "№";
    heading_1.style = 'text-align: center';
    let heading_2 = document.createElement('th');
    heading_2.innerHTML = "Author (full name)";
    heading_2.style = 'text-align: center';
    let heading_3 = document.createElement('th');
    heading_3.innerHTML = "Author's id";
    heading_3.style = 'text-align: center';
    let heading_4 = document.createElement('th');
    heading_4.innerHTML = "Operations";
    heading_4.style = 'text-align: center';
    row_0.appendChild(heading_1);
    row_0.appendChild(heading_2);
    row_0.appendChild(heading_3);
    row_0.appendChild(heading_4);
    thead.appendChild(row_0);
    let row_n;
    let row_n_data_1;
    let row_n_data_2;
    let row_n_data_3;
    let row_n_data_4;
    for (let i = 0; i < authorDtoList.length; i++) {
        row_n = document.createElement('tr');
        row_n_data_1 = document.createElement('td');
        row_n_data_1.innerHTML = (i + 1);
        row_n_data_2 = document.createElement('td');
        row_n_data_2.innerHTML = authorDtoList[i].fullName;
        row_n_data_2.style = 'text-align: center';
        row_n_data_3 = document.createElement('td');
        row_n_data_3.innerHTML = authorDtoList[i].id;
        row_n_data_3.style = 'text-align: center';
        row_n_data_4 = document.createElement('td');
        row_n_data_4.innerHTML = '<button>Edit</button> ' + ' ' + '<button>Delete</button>';
        row_n_data_4.style = 'text-align: center';
        row_n.appendChild(row_n_data_1);
        row_n.appendChild(row_n_data_2);
        row_n.appendChild(row_n_data_3);
        row_n.appendChild(row_n_data_4);
        tbody.appendChild(row_n);
    }
}