
//거래 등록
{
    let radioButtons = document.querySelectorAll('input[name="transactionType"]');

    const categoryInput = document.querySelector('#categoryId-input');
    const additionalInfoSection = document.querySelector('#expense_additional-information');

    const categoryInfoList = additionalInfoSection.querySelector('#category-info');
    const paymentInfoList = additionalInfoSection.querySelector('#payment-info');

    // 지출 선택 시 카테고리 출력
    radioButtons.forEach(button =>{
        button.addEventListener('click', function(e){
            if(e.target.value === "EXPENSE"){
                additionalInfoSection.style.display = 'block';
            }else{
                additionalInfoSection.style.display = 'none';
            }
        })
    })

    // 카테고리 선택 시 INPUT에 VALUE 추가 / 카테고리 선택 색 변경
    toggleSelection(categoryInfoList);
    toggleSelection(paymentInfoList);

function toggleSelection(list){
    const input = list.querySelector('input');

    list.addEventListener('click', function(e){

        if(e.target.tagName==="SPAN"){
            if(input.value !== ""){
                e.currentTarget.querySelector('.choice').classList.remove('choice');
            }
            input.value = e.target.dataset.id;
            e.target.classList.add('choice');
        }
    })
}
}


// 달력 date 클릭 시 해당 일자의 거래내역
{
    const year = document.querySelector('.year').innerText;
    const month = document.querySelector('.month').innerText;
    const dates = document.querySelector('.dates');

    const lists = document.querySelector('.list-content');


    dates.addEventListener('click', function (e){
        const transactionDate = `${year}-${month}-${e.target.dataset.day}`;
        console.log('transactionDate',transactionDate)

        fetch(`/transaction-api/daily/transaction-list/${transactionDate}`,{
            credentials: 'same-origin'
        }).then(resp => resp.json())
            .then(data => {
                lists.innerHTML = "";
                data.forEach(list => {
                    const div = document.createElement('div');
                    div.classList.add('list-item');

                    console.log('list',list);

                    div.innerHTML = `
                        <span>${list.transactionTitle}</span>
                        <span>${list.transactionAmount}</span>
                        <span>${list.categoryName}</span>
                        <span>${list.paymentMethodName}</span>
                        <span>${list.memo}</span>
                    `;

                    lists.appendChild(div);
                })
            })

    })

}

