
function [beats_per_minute, bradycardia_instances]=  HeartRateCalculator(fileName, colIndex)
ecg_data = load(fileName);
Rpeakindex = RPeakDetection(ecg_data(:,colIndex));

instances_per_minute = 128 * 60;

count = 0;
beats_per_minute = [];
bradycardia_instances = [];
bundle = instances_per_minute;
index = 1;
minute = 0;
while bundle <= length(ecg_data)
	while index ~= length(Rpeakindex) && Rpeakindex(index) <= bundle
		count = count + 1;
		index = index + 1;
	end
	beats_per_minute = [beats_per_minute, count];
	minute = minute + 1;
	if count <= 60
		temp =  [minute, count];
		bradycardia_instances = [bradycardia_instances ; temp];
	end 
	count = 0;
	bundle = bundle + instances_per_minute;
end

% plot(beats_per_minute,'b');
% title('Heart Rate Variation');
% xlabel('Time in minutes');
% ylabel('Beats per minute');

% 
% if(~isempty(bradycardia_instances))
%     disp("BradyCardia Detected !");
%     hold on;
%     scatter(bradycardia_instances(:,1),bradycardia_instances(:,2),'b','filled');
% else 
%     disp("No BradyCardia Detected !");
% end