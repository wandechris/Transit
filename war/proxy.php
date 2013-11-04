<?php

header('Content-Type: application/json');
$parArr = array();
$url = "http://developer.itsmarta.com/BRDRestService/BRDRestService.svc/GetBusByRoute/110";
//Get parameter from the calling script

	foreach ($_REQUEST as $key => $value) {
		if ($key == "fmsisdn" || $key == "msisdn")
			$value = str_replace("+", "", $value);
		$parArr[] = $key . "=" . $value;
	}
	$param = implode("&", $parArr);
	$response = get_url_content($url, $param);
	//echo $response;
//Call the new url $url

	function get_url_content($url, $param_string) {
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_URL, $url . $param_string);
		// curl_setopt($ch,CURLOPT_URL,$url."?".$param_string);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		//Optional set timeout :: curl_setopt($ch,CURLOPT_CONNECTTIMEOUT,$timeout);
		$data = curl_exec($ch);
		curl_close($ch);
		return $data;
	}
// echo "url = " . $url . $param;
 echo $response;?>