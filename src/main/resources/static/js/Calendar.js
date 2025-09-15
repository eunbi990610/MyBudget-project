
// 달력 화면 구현
{
    let year = document.querySelector('.year').innerText;
    let month = document.querySelector('.month').innerText;//9월

    const monthLastDate = new Date(year, month,0).getDate();//9월의 마지막날
    const pervMonthLastDay = new Date(year, month-1,0).getDay()+1;//8월의 마지막 요일


    const datesArray = Array.from({length:monthLastDate}, (_, index) => index +1);
    for(let i=0; i<pervMonthLastDay; i++){
        datesArray.unshift('');
    }

    const dates = document.querySelector('.dates');

    datesArray.forEach(i => {
        let div = document.createElement('div');
        let span = document.createElement('span');
        let dailyExpenseTotal = document.createElement('span')


        div.className = 'date';
        div.dataset.day = String(i);
        span.className = 'date-str';
        span.textContent = i;

        dailyExpenseTotal.className = 'sumExpense';

        dates.appendChild(div);
        div.appendChild(span);
        div.appendChild(dailyExpenseTotal);
    })

    const pervBtn = document.querySelector('#nav-before');
    const nextBtn = document.querySelector('#nav-after');

    pervBtn.addEventListener('click', ()=>{
        console.log("이전달로 전환");
        if(month==="1"){
            window.location.href = `/transactions/daily?year=${--year}&month=${12}`;
            return;
        }
        window.location.href = `/transactions/daily?year=${year}&month=${--month}`;
    });
    nextBtn.addEventListener('click', ()=>{
        console.log("다음달로 전환");
        if(month==="12"){
            window.location.href = `/transactions/daily?year=${++year}&month=${1}`
            return;
        }
        window.location.href = `/transactions/daily?year=${year}&month=${++month}`;
    });


}

{
    function firstOfMonthStr(year, month){
        const mm = String(month).padStart(2,'0');
        return `${year}-${mm}-1`;
    }


    async function fillDailySums(year, month){

        let firstOfMonth = firstOfMonthStr(year, month);

        const res =
            await fetch(`/transaction-api/daily-sum/${firstOfMonth}`,{
            credentials: 'same-origin' // 같은 도메인 요청에 한해 쿠키/세션 포함?
        });

        if (!res.ok) throw new Error(`HTTP ${res.status}`);

        const list = await res.json();

        const map = new Map(list.map(row => [
            Number(row.day ?? row.DAY),
            Number(row.sum ?? row.SUM ?? 0)
        ]));

        document.querySelectorAll('.date[data-day]').forEach(div => {
            const day = Number(div.dataset.day);
            const sum = map.get(day) ?? 0;
            const target = div.querySelector('.sumExpense');
            if(!target) return;
            target.textContent = String(sum);
        })

    }

    let year = document.querySelector('.year').innerText;
    let month = document.querySelector('.month').innerText;//9월

    document.addEventListener('DOMContentLoaded', () =>{
        console.log('DOMContentLoaded');
        fillDailySums(year, month);
    });


}
