
// 카테고리 삭제 리팩토링 코드 -> 로딩 시간 단축
{
    const categoryList = document.querySelector('#category-lists');
    categoryList.addEventListener('click', function (e) {
        // 카테고리 삭제
        if (e.target.className === 'drop_category-btn') {
            console.log('카테고리 삭제 버튼 클릭했음!!')
            const dropBtn = e.target.closest('.drop_category-btn');
            const dropRow = e.target.closest('.expense-category');

            if (dropBtn.dataset.loading === '1')
                return;
            dropBtn.dataset.loading = '1';
            dropBtn.disabled = true;
            // disabled :  html 표준 속성인 disabled를 버튼 요소에 직접 사용 => 클릭/입력 불가능 속성
            // html의 <button>,<input>에 붙일 수 있는 속성

            // ui 먼저 제거 (체감 지연 0에 가깝게)
            const parent = dropRow.parentElement;
            parent.removeChild(dropRow);
            // 카테고리 삭제 시

            fetch("/budget-api/drop-category", {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    "categoryId": dropRow.dataset.id
                })
            }).then(response => {
                if (!response.ok) {
                    throw new Error("지출 카테고리 삭제 실패");
                }
            }).catch(error => {
                parent.appendChild(dropRow);
                console.error("지출 카테고리 삭제 중 에러 발생 :", error);
                dropBtn.dataset.loadng = '0';
                dropBtn.disabled = false;
            });
        }

        //  카테고리 이름 수정
        else if (e.target.className === 'modify_category-btn') {
            console.log('카테고리 수정 버튼 클릭 했음~!!');
            const modifyRow = e.target.closest('.expense-category');
            const buttons = modifyRow.querySelectorAll('.buttons button');
            const modifyBtn = modifyRow.querySelector('.modify_finish-btn');
            const resetBtn = modifyRow.querySelector('.reset-btn');
            const modifyInputRow = modifyRow.querySelector('.modify_name-input');
            const categoryNameRow = modifyRow.querySelector('.category-name');
            const errorMsg = modifyRow.querySelector('.error-msg');

            // 수정 버튼/입력바 화면 출력
            changeButtons(categoryNameRow, modifyInputRow, buttons);

            resetBtn.addEventListener('click', () => {
                console.log('수정 취소 버튼클릭');
                changeButtons(categoryNameRow, modifyInputRow, buttons);
                modifyInputRow.querySelector('input').value = categoryNameRow.innerText;
            }, {once: true});

            // fetch()
            // 카테고리 이름 수정 -> fetch _ "method : patch"
            modifyBtn.addEventListener('click', () => {
                const modifyInput = modifyInputRow.querySelector('input');
                const newName = modifyInput.value;
                console.log('categoryName :', categoryNameRow.innerText);
                console.log('newName : ', newName);

                if (newName === categoryNameRow.innerText) {
                    errorMsg.innerText = '수정 전 이름과 동일합니다.';
                } else if (!newName) {
                    errorMsg.innerText = '수정할 이름을 작성해주세요.';
                } else {
                    fetch(`/budget-api/modify-categoryName`, {
                        method: 'PATCH',
                        headers: {'Content-Type': 'application/json'},
                        body: JSON.stringify(
                            {
                                categoryName: newName,
                                categoryId: modifyRow.dataset.id //dataset.id = 문자열
                            })
                    }).then(response => {
                        if (!response.ok) {
                            errorMsg.innerText = '동일한 카테고리이름이 존재합니다.';
                            throw new Error('카테고리 이름 변경 실패');
                        }
                        changeButtons(categoryNameRow, modifyInputRow, buttons);
                        categoryNameRow.innerText = newName;
                    }).catch(error => {
                        // 수정버튼을 누르고 innerText 문구가 화면에 출력하기 까지 오래 걸림.
                        console.error("지출 카테고리 이름 변경 중 에러 발생 :", error);
                    })
                }
            }, {once: true})
        }

    })


}

// 카테고리 변경 시 여러개의 리스트가 동시에 수정 input이 보이게됨
// 하나의 리스트를 수정할 때 다른 리스트의 수정버튼은 불가능하게 처리해야함!!



// 예상 지출 예산 등록/수정하기
{
    let budgetLists = document.querySelector('#budget-lists');
    budgetLists.addEventListener('click', function(event){
        const clickRow = event.target.closest(".budget-list");
        const budgetAmount = clickRow.querySelector('.budget-amount');
        const modifyInput = clickRow.querySelector('.modify-input');
        const buttons = clickRow.querySelectorAll('.buttons button');

        // console.log('clickTarget',event.target);
        // console.log('clickRow',clickRow);
        // console.log('clickRow-className',clickRow.className);
        if(event.target.className === 'modify-btn'){
            changeButtons(budgetAmount, modifyInput, buttons);

            clickRow.querySelector('.modify-reset_btn')
                .addEventListener('click', ()=>{
                    console.log('수정 취소 버튼클릭');
                    changeButtons(budgetAmount, modifyInput, buttons);
                    modifyInput.querySelector('input').value = budgetAmount.innerText;
            //      예산을 변경하지 않고 취소버튼을 누르면 입력창이 사라지지 않음 !!!
            },{once: true});

            clickRow.querySelector('.modify-finish_btn')
                .addEventListener('click', function(e){
                    // modifyInput.querySelector('input').value = budgetAmount.innerText;
                    const newBudgetAmount = modifyInput.querySelector('input').value;
                    console.log('newBudgetAmount',newBudgetAmount);

                    fetch(`/budget-api/modify-budgetAmount`,{
                        method : 'PATCH',
                        headers : {'Content-type' : 'application/json'},
                        body : JSON.stringify({
                            budgetId : clickRow.dataset.id,  //String
                            newBudgetAmount : newBudgetAmount.replace(/,/g, '')
                        })
                    }).then(resp =>{
                        // console.log(resp);
                        if (resp.status !== 200) throw new Error("예산 금액 수정 중 오류 발생_/budget-api/modify-budgetAmount")
                        budgetAmount.innerText = newBudgetAmount;
                        changeButtons(budgetAmount, modifyInput, buttons);
                    }).catch(error=>{
                        // budgetAmount.innerText = newBudgetAmount;
                        console.log("예산 금액 수정 중 오류 발생 : ", error);
                    })

                });

        }

    })
}

{
    function changeButtons(row1, row2, buttons) {
        row1.classList.toggle('hidden');
        row2.classList.toggle('hidden');
        buttons.forEach(button => {
            button.classList.toggle('hidden');
        })
        console.log(`row1 : ${row1.classList}, row2 : ${row2.classList}`);
    }
}

// 월 변경 이벤트
{
    let year = document.querySelector('#year').innerText;
    let month = document.querySelector('#month').innerText;

    console.log(`year : ${year}, month : ${month}`);
    let calenderNav = document.querySelector("#calendar-navigation-buttons");
    calenderNav.addEventListener('click', function(e){
        if(e.target.id === "prev-btn"){
            if(month===1){
                year--;
                month = 13;
            }
            month--;
            console.log("전달로 전환", e.target, "month : ", month, "year",year);
            e.target.href = `/expected/budget?year=${year}&month=${month}`;
        }else if(e.target.id === "next-btn"){
            if(month===12){
                year++;
                month = 0;
            }
            month++;
            console.log("다음달로 전환", e.target,"month : ", month, "year",year);
            e.target.href = `/expected/budget?year=${year}&month=${month}`;
        }
    })

    function createForm(){

    }

}

