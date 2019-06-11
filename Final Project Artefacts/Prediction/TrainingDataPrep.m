A = [];
for a=0:1
files = dir(strcat(string(a),"\ekg*.csv"));
    for i = 1:size(files,1)
        disp(files(i).name);
        data = load(strcat(files(i).folder,'\',files(i).name));
        for b = 1:20
            for j = b:3:size(data,1)
                if (j+3) < 30
                    m = mean(data(j:j+3,2),1);
                    v = var(data(j:j+3,2),1);
                    A = [A; m,v,a];
                end 
            end
        end
    end
end
csvwrite('train_data_mean.csv',A);