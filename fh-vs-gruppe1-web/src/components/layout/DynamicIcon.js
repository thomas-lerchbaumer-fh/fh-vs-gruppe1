
import * as Muicon from "@mui/icons-material"

const DynamicIcon = ({ name, ...rest }) => {
    const IconComponent = Muicon[name];
    return IconComponent ? <IconComponent {...rest} /> : null;
};


export default DynamicIcon