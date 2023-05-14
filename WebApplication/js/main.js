function cleanForm()
{
    $('#brand').val(''),
    $('#breeds').val(''),
    $('#foodName').val(''),
    $('#price').val(''),
    $('#weightkg').val('')
}

function showAll() 
{
    fetch('http://localhost:8080/api/dogfood')
      .then(response => response.json())
      .then(data => {
        let tableRows = '';
        data.forEach(element => {
          tableRows += `
            <tr dogFoodID = ${element.id} >
              <td>${element.id}</td>
              <td>${element.brand}</td>
              <td>${element.breeds}</td>
              <td>${element.foodName}</td>
              <td>$${element.price}</td>
              <td>${element.weightKG}</td>
              <td>
                <button id="btn-delete" class="btn btn-danger">Eliminar</button>
              </td>
              <td>
                <button id="btn-edit" class="btn btn-warning">Editar</button>
              </td>
            </tr>
          `;
        });
        document.querySelector('#tbody').innerHTML = tableRows;
      })
      .catch(error => console.error('Error:', error));
}
  
function saveDogFood() 
{
    document.querySelector('#save').addEventListener('click', function() {
        const dataDogFood = {
          brand: document.querySelector('#brand').value,
          breeds: document.querySelector('#breeds').value,
          foodName: document.querySelector('#foodName').value,
          price: document.querySelector('#price').value,
          weightKG: document.querySelector('#weightkg').value,
        };
    
        fetch('http://localhost:8080/api/save', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(dataDogFood)
        })
          .then(response => response.json())
          .then(data => {
            $('#messages').html("Your Dog Food was succesfully created").css('display:','block')
            showAll();
            cleanForm();
            console.log('Dog Food Registered');
          })
          .catch(error => console.error('Error:', error));
      });
}

function deleteDogFood()
{
    document.addEventListener('click', function(event) 
    {
        const target = event.target;
        if (target.matches('#btn-delete')) 
        {
          if (confirm('Are you sure that you want to delete ?')) 
          {
            const btnDetails = target.parentElement.parentElement;
            const id = btnDetails.getAttribute('dogFoodID');
            fetch(`http://localhost:8080/api/delete/${id}`, {
              method: 'DELETE',
              headers: {
                'Content-Type': 'application/json'
              }
            })
              .then(response => {
                if (response.ok) {
                  return response.json();
                } else {
                  throw new Error('Network response was not ok.');
                }
              })
              .then(() => {
                showAll();
              })
              .catch(error => console.error('Error:', error));
          }
        }
    });
}

function fillDogFood()
{
    $(document).on('click', '#btn-edit', function() {
        let btnEdit = $(this)[0].parentElement.parentElement;
        let id = $(btnEdit).attr('dogFoodID');
    
        $('#save').hide();
        $('#edit').show();
    
        fetch('http://localhost:8080/api/dogfood/' + id)
          .then(response => response.json())
          .then(res => {
            $('#id').val(res.id);
            $('#brand').val(res.brand);
            $('#breeds').val(res.breeds);
            $('#foodName').val(res.foodName);
            $('#price').val(res.price);
            $('#weightkg').val(res.weightKG);
          })
          .catch(error => console.log(error));
      });
}

function updateDogFood() 
{
    const editButton = document.getElementById('edit');

    editButton.addEventListener('click', function() 
    {
        const id = document.getElementById('id').value;
        const dataDogFood = {
          brand: document.querySelector('#brand').value,
          breeds: document.querySelector('#breeds').value,
          foodName: document.querySelector('#foodName').value,
          price: document.querySelector('#price').value,
          weightKG: document.querySelector('#weightkg').value,
        };

        fetch('http://localhost:8080/api/update/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dataDogFood)
        })
            .then(response => response.json())
            .then(data => {
            $('#messages').html('Dog Food Edited').css('display', 'block')
            $('#edit').css('display', 'none');
            $('#save').css('display', 'block');

            showAll();
            cleanForm();
        })
        .catch(error => console.error(error));
    });
}

document.addEventListener('DOMContentLoaded', () => {
    showAll();
    saveDogFood();
    deleteDogFood();
    fillDogFood();
    updateDogFood();
});
  