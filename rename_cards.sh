ranks=(A 1 2 3 4 5 6 7 8 9 10 J Q K)
suits=(D C H S)

count=1

for rank in "${ranks[@]}"; do
  for suit in "${suits[@]}"; do
    oldname="${rank}${suit}.png"
    if [ -f "$oldname" ]; then
      mv "$oldname" "${count}.png"
      echo "Renamed $oldname to ${count}.png"
      ((count++))
    fi
  done
done