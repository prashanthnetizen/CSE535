% Bradycardia Detection Algorithm

 HeartRates = HeartRateCalculator('ekg_raw_16272.dat',2); % First Argument is fileName, 2nd Argument is column (2 or 3)
 
 newHeartRates = zeros(30,1);

 for i = 1:28
     newHeartRates(i)= mean([HeartRates(i),HeartRates(i+1),HeartRates(i+2)]);
 end
 
 newHeartRates(29) = newHeartRates(28);
 newHeartRates(30) = newHeartRates(28);
 
 bradycardia_instances = [];
 
 fp = 0;
 fn = 0;
 
 for i = 1:30
     if  newHeartRates(i) <= 60
          temp = [i, newHeartRates(i)]; 
          bradycardia_instances = [bradycardia_instances; temp]; 
     end
     
     if( (newHeartRates(i) > 60) && (HeartRates(i) <= 60) )
       fp = fp +1;
     elseif ( newHeartRates(i) <= 60 && HeartRates(i) > 60 )
       fn = fn +1;
     end
      
 end
 
plot(newHeartRates);
title('Heart Rate Variation');
xlabel('Time in minutes');
ylabel('Beats per minute');

if(~isempty(bradycardia_instances))
    disp(strcat("BradyCardia Detected ! ", newline, " False Positives Rate : " , string(fp/30) , newline," False Negative Rate : ", string(fn/30)));
    hold on;
    scatter(bradycardia_instances(:,1),bradycardia_instances(:,2),'r','filled');
else 
    disp("No BradyCardia Detected !");
end
 
 