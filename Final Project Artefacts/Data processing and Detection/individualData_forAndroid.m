files = dir('*.dat');
final_data_set = [];
for i = 1:size(files,1)
	for j= 2:3
		disp(files(i).name);
		[A,B] = HeartRateCalculator(files(i).name,j);
		temp_rates = [[1:30]', A'];
        csvwrite(strcat(files(i).name(1:end-4),'_',string(j),'.csv'),temp_rates);
	end
end