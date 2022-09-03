button_enter.onclick = function () {
    let request = new XMLHttpRequest();
    request.open("GET", "/api/books");
    request.send();
    request.onload = function () {
        if (request.status = 200) {

            title.innerHTML = "Books";
            let bookDtoList = JSON.parse(request.response);
            let table = document.createElement('table');
            document.getElementById('main_div').innerHTML = '';

            let p = document.createElement('p');
            document.getElementById('main_div').appendChild(p);
            let span = document.createElement('span');
            p.appendChild(span);
            p.innerHTML = 'Library Books </br>';
            p.style = 'font-weight: bold';
            let img = document.createElement('img');
            p.appendChild(img);
            img.src = '/library.png';

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
            heading_2.innerHTML = "Title";
            heading_2.style = 'text-align: center';
            let heading_3 = document.createElement('th');
            heading_3.innerHTML = "Author";
            heading_3.style = 'text-align: center';
            let heading_4 = document.createElement('th');
            heading_4.innerHTML = "Genre";
            heading_4.style = 'text-align: center';
            let heading_5 = document.createElement('th');
            heading_5.innerHTML = "Id book";
            heading_5.style = 'text-align: center';
            let heading_6 = document.createElement('th');
            heading_6.innerHTML = "Operations";
            heading_6.style = 'text-align: center';
            row_0.appendChild(heading_1);
            row_0.appendChild(heading_2);
            row_0.appendChild(heading_3);
            row_0.appendChild(heading_4);
            row_0.appendChild(heading_5);
            row_0.appendChild(heading_6);
            thead.appendChild(row_0);
            let row_n;
            let row_n_data_1;
            let row_n_data_2;
            let row_n_data_3;
            let row_n_data_4;
            let row_n_data_5;
            let row_n_data_6;
            for (let i = 0; i < bookDtoList.length; i++) {
                row_n = document.createElement('tr');
                row_n_data_1 = document.createElement('td');
                row_n_data_1.innerHTML = (i + 1);
                row_n_data_2 = document.createElement('td');
                row_n_data_2.innerHTML = bookDtoList[i].title;
                row_n_data_2.style = 'text-align: center';
                row_n_data_3 = document.createElement('td');
                row_n_data_3.innerHTML = bookDtoList[i].authorFullName;
                row_n_data_3.style = 'text-align: center';
                row_n_data_4 = document.createElement('td');
                row_n_data_4.innerHTML = bookDtoList[i].genreName;
                row_n_data_4.style = 'text-align: center';
                row_n_data_5 = document.createElement('td');
                row_n_data_5.innerHTML = bookDtoList[i].id;
                row_n_data_5.style = 'text-align: center';
                row_n_data_6 = document.createElement('td');
                row_n_data_6.innerHTML = '<button>Edit</button> ' + ' ' + '<button>Delete</button>';
                row_n_data_6.style = 'text-align: center';
                row_n.appendChild(row_n_data_1);
                row_n.appendChild(row_n_data_2);
                row_n.appendChild(row_n_data_3);
                row_n.appendChild(row_n_data_4);
                row_n.appendChild(row_n_data_5);
                row_n.appendChild(row_n_data_6);
                tbody.appendChild(row_n);
            }
        } else {
            alert(`Ошибка ${request.status}: ${request.statusText}`);
        }
    }
}