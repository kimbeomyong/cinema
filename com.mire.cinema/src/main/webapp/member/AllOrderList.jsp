<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis 게시판 - 영화관 리스트</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.nav-link {
	color: white;
}
</style>
</head>
<body>
	<header>
		<%@ include file="../WEB-INF/header.jsp"%>
	</header>
	<div class="container mt-5">
	<div class= "container-fluid  d-flex justify-content-between">
		<h2 class="mb-4">전체 주문 내역</h2>
		</div>
		</div>
		<table id="cinema-list" class="table table-striped container">
			<thead>
				<tr>
					<th>주문번호</th>
					<th>회원아이디</th>
					<th>상품이름</th>
					<th>상품수량</th>
					<th>상품 총 가격</th>
					<th>할인된 가격</th>
					<th>주문상태</th>
					<th>주문일</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody id= "orderList">
			</tbody>
		</table>

		<div id="paging" class="d-flex container justify-content-center mt-5">
			<div id="prev" class="mx-4"></div>
			<div id="pageNum"></div>
			<div id="next" class="mx-4"></div>
		</div>
	</div>


	<footer class="container mt-5">
		<%@ include file="../WEB-INF/footer.jsp"%>
	</footer>
</body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
document.addEventListener('DOMContentLoaded', function () {
   	var memberId = '${memberId}';
    fetchMembers(1); // Initial fetch with page 1
});

function fetchMembers(pageNum) {
	
	
	var url =  '/pay/list/' + pageNum;
	console.log(url);
    fetch(url, {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => {
            if (!response.ok) {
                return response.json().then(errorData => {
                    alert("서버내부 오류: " + errorData.message);
                    throw new Error('Server error');
                });
            }
            return response.json();
        })
        .then(data => {
           
            console.log(data.list);
            console.log(data.page);
          
            let orders = (data.list === undefined)?data.searchList:data.list;
            let paginationData = data.page;
            createPaginationButtons(paginationData.beginPage, paginationData.endPage, paginationData.prev, paginationData.next,data);
            displayMovies(orders);

        })
        .catch(error => {
            console.error('Fetch error:', error.message);
        });
}

function displayMovies(orders) {
   
    $('#orderList').empty();
	
  
    for (var order of orders) {
    
        let orderInfo = 
        	
        	
        	'<tr>' +
            '<td>' + order.orderId + '</td>' +
            '<td>' + order.memberId + '</td>' +
            '<td>' + order.itemName + '</td>' +
            '<td>' + order.orderQuantity + '</td>' +
            '<td>' + order.orderPrice + '</td>' +
            '<td>' + order.discountPrice + '</td>' +
            '<td>' + order.orderStatus + '</td>' +
            '<td>' + order.orderDate + '</td>' +
            (order.orderStatus === 'COMPLETE' ?
                '<td><div class="btn btn-dark" onclick="cancel(\'' + order.orderId + '\')">주문취소</div></td>' :
                '') +
        '</tr>';
           
        $('#orderList').append(orderInfo);
        
    }
    
}
async function cancel(id) {
	console.log(id);
	var check = confirm('취소하시겠습니까?');
	if(check){
    var url = '/pay/' + id;
    const response = await fetch(url, {
        method: "DELETE", // Corrected method name
    });
    location.reload();
    console.log(response);
	}
}
 
 
 
function createPaginationButtons(begin, end, prev, next,data) {
    let prevPage = begin - 1;
    let nextPage = end + 1
    $('#pageNum').empty();
    
    if(data.list !== undefined){
   	 	$('#prev').html(prev ? '<button onclick="fetchMembers(' + prevPage + ')">이전</button>' : '');
    	$('#next').html(next ? '<button onclick="fetchMembers(' + nextPage + ')">다음</button>' : '');
    	  for (let i = begin; i <= end; i++) {
    	        $('#pageNum').append('<button onclick="fetchMembers(' + i + ')" class="mx-2">' + i + '</button>');
    	    }
    }
    else{	
    	console.log(data.keyword);
    		$('#prev').html(prev ? '<button onclick="fetchMembers(' + prevPage + ', \'' + data.keyword + '\')">이전</button>' : '');
    		$('#next').html(next ? '<button onclick="fetchMembers(' + nextPage + ', \'' + data.keyword + '\')">다음</button>' : '');
    		 for (let i = begin; i <= end; i++) {
    		        $('#pageNum').append('<button onclick="fetchMembers(' + i + ', \'' + data.keyword + '\')"lass="mx-2">' + i + '</button>');
    		    }
    }
   
    
   
   
}
</script>
</html>